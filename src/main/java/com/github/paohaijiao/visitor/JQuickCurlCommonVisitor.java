/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.enums.JHttpMethod;
import com.github.paohaijiao.enums.JProxryType;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.model.*;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickCurlLexer;
import com.github.paohaijiao.parser.JQuickCurlParser;
import com.github.paohaijiao.util.JStringUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Base64;
@Slf4j
public class JQuickCurlCommonVisitor extends JQuickCurlCoreVisitor {
    public JQuickCurlCommonVisitor(JContext context){
      this.context = null==context?new JContext():context;
      this.config =  JQuickCurlConfig.getInstance();
    }
    public JQuickCurlCommonVisitor(JQuickCurlConfig config){
        this.context = null==context?new JContext():context;
        this.config =  config;
    }
    public JQuickCurlCommonVisitor(JContext context,JQuickCurlConfig config){
        this.context = null==context?new JContext():context;
        this.config =  config;
    }
    public JQuickCurlCommonVisitor( ){
        this.context = new JContext();
    }

    @Override
    public JResult visitCurlCommand(JQuickCurlParser.CurlCommandContext ctx) {
        for (JQuickCurlParser.OptionContext option : ctx.option()) {
            visitOption(option);
        }
        for (JQuickCurlParser.UrlContext urlCtx : ctx.url()) {
            this.url = visitUrl(urlCtx);
        }
        JAssert.notNull(this.method ,"The specified httpMethod must be displayed eg -X POST|GET|DELETE|PUT etc.....");
        this.client = getOkHttpClient();
        Request.Builder builder  = new Request.Builder().url(url);
        RequestBody body=null;
        if(null!=this.data){
            body=RequestBody.create(this.data, MediaType.parse(ContentType));
        }else{//MediaType.parse(ContentType)
            body=RequestBody.create("{}", MediaType.parse(ContentType));
        }
        if(JHttpMethod.requireNotHaveRequestBody(this.method)){
            builder.method(this.method, null);
        }else{
            builder.method(method, body);
        }

        headerList.forEach(e->{
            builder.addHeader(e.getKey(),e.getValue());
        });
        if(StringUtils.isNotBlank(this.credential)){
            builder.addHeader("Authorization",this.credential);
        }
        Request request=builder.build();
        if(JHttpMethod.HEAD.getCode().equals(this.requestType)){
            builder.head().build();
        }
        MultipartBody.Builder mutiPartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for(JFormParam formParam:upLoadFileList){
            if(formParam.isFile()){
                String fileName= JStringUtils.trim(formParam.getValue());
                File file = new File(fileName);
                RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
                mutiPartBuilder.addFormDataPart(formParam.getKey(), file.getName(), fileBody);
            }else{
                System.out.println(formParam.getValue());
                RequestBody fileBody = RequestBody.create( formParam.getValue(), MediaType.parse("application/json"));
                mutiPartBuilder.addFormDataPart(formParam.getKey(),null ,fileBody).build();
            }
        }
        if(upLoadFileList.size()>0){
            request=request.newBuilder().post(mutiPartBuilder.build()).build();
        }

        try  {
            Response response = client.newCall(request).execute();
            log.info("Response:{}",response);
            if (!response.isSuccessful()){
                System.out.println("Cannot request the resource");
            };
            Headers responseHeaders = response.headers();
            ResponseBody responseData = response.body();
            MediaType contentType = body.contentType();
            if(contentType != null && contentType.type().equals("text/plain")) {
                return null;
            }
            JResult result=new JResult();
            result.setMediaType(MediaType.parse(ContentType));
            if (ContentType != null){
                com.github.paohaijiao.enums.MediaType mediaType=com.github.paohaijiao.enums.MediaType.codeOf(ContentType);
                if (mediaType != null&& mediaType.isBinary()){
                    byte[] bytes= responseData.bytes();
                    result.setBytes(bytes);
                    return result;
                }
            }
            if (downLoadFileName != null) {
                byte[] bytes= downLoadFile(responseData);
                result.setBytes(bytes);
            }
            String string=responseData.string();
            if(JHttpMethod.HEAD.getCode().equals(this.requestType)){
                result.setString(responseHeaders.toString());
            } else if(JHttpMethod.OPTIONS.getCode().equals(this.method)){
                JOptionModel model=new JOptionModel();
                model.setHttpStatus(response.code());
                model.setHeaders(responseHeaders);
                String allow= response.headers("Allow").toString();
                model.setAllow(allow);
                result.setString(model.toString());
            }else if(null!=string){//only support get once
                result.setString(string);
            }else{
                byte[] bytes= responseData.bytes();
                if(bytes!=null){
                    result.setBytes(bytes);
                }else{
                    InputStream byteStream = responseData.byteStream();
                    if(null!=byteStream){
                        result.setByteStream(byteStream);
                    }else {
                        Reader charStream= responseData.charStream();
                        if(null!=charStream){
                            result.setCharStream(charStream);
                        }else{
                            ByteString byteString=responseData.byteString();
                            if(null!=byteString){
                                result.setByteString(byteString);
                            }
                        }
                    }
                }
            }
            result.setContentLength(responseData.contentLength());
            result.setSource(responseData.source());
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object visitOption(JQuickCurlParser.OptionContext ctx) {
        if (ctx.requestMethod() != null) {
            visitRequestMethod(ctx.requestMethod());
        } else if (ctx.headerOption() != null) {
            visitHeaderOption(ctx.headerOption());
        } else if (ctx.dataOption() != null) {
            visitDataOption(ctx.dataOption());
        } else if (ctx.dataUrlEncodeOption() != null) {
            visitDataUrlEncodeOption(ctx.dataUrlEncodeOption());
        } else if (ctx.userOption() != null) {
            visitUserOption(ctx.userOption());
        } else if (ctx.locationOption() != null) {
            visitLocationOption(ctx.locationOption());
        } else if (ctx.otherOption() != null) {
            visitOtherOption(ctx.otherOption());
        } else if (ctx.downloadOption() != null) {
            visitDownloadOption(ctx.downloadOption());
        }else if (ctx.uploadOption() != null) {
            visitUploadOption(ctx.uploadOption());
        }else if (ctx.proxryOption() != null) {
            visitProxryOption(ctx.proxryOption());
        }else if (ctx.socketOption() != null) {
            visitSocketOption(ctx.socketOption());
        }else if (ctx.http2Option() != null) {
            visitHttp2Option(ctx.http2Option());
        }else if (ctx.ignoreOption() != null) {
            visitIgnoreOption(ctx.ignoreOption());
        }
//        else if(ctx.headOption() != null) {
//            visitHeadOption(ctx.headOption());
//        }
        return null;
    }
//    @Override
//    public String visitHeadOption(JQuickCurlParser.HeadOptionContext ctx) {
//        this.requestType= JHttpMethod.HEAD.getCode();
//        return null;
//    }

    /**
     * PASS
     * @param ctx the parse tree
     * @return
     */
    @Override
    public JHttpMethod visitRequestMethod(JQuickCurlParser.RequestMethodContext ctx) {
        this.method = ctx.method.getText();
        JHttpMethod method= JHttpMethod.codeOf(this.method);
        return method;
    }

    /**
     * PASS
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Void visitHeaderOption(JQuickCurlParser.HeaderOptionContext ctx) {
        String header = ctx.headerValue.getText().replaceAll("^['\"]|['\"]$", "");
        String[] parts = header.split(":", 2);
        if (parts.length == 2) {
            JHeaderParam jHeaderParam = new JHeaderParam();
            jHeaderParam.setKey(parts[0]);
            jHeaderParam.setValue(parts[1]);
            headerList.add(jHeaderParam);
            if("Content-Type".equalsIgnoreCase(parts[0])) {
                this.ContentType=parts[1];
            }
        }
        return null;
    }

    /**
     * PASS
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitDataOption(JQuickCurlParser.DataOptionContext ctx) {
        String data = ctx.dataValue.getText().replaceAll("^['\"]|['\"]$", "");
        this.data=data;
        return null;
    }

    @Override
    public Object visitDataUrlEncodeOption(JQuickCurlParser.DataUrlEncodeOptionContext ctx) {
        String data;
        if (ctx.emptyData() != null) {
            data = "";
        } else {
            data = ctx.formData().getText().replaceAll("^['\"]|['\"]$", "");
        }
        this.ContentType="application/x-www-form-urlencoded";
        return null;
    }

    @Override
    public Object visitEmptyData(JQuickCurlParser.EmptyDataContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Object visitFormData(JQuickCurlParser.FormDataContext ctx) {
        return visitChildren(ctx); }

    @Override
    public String visitUserOption(JQuickCurlParser.UserOptionContext ctx) {
        JCredentials credentials= visitCredentials(ctx.credentials());
        JAssert.notNull(credentials,"credential is null");
        String credentialString=Credentials.basic(credentials.getUsername(), credentials.getPassword());
        this.credential=credentialString;
        return credentialString;
    }
    @Override
    public JCredentials visitCredentials(JQuickCurlParser.CredentialsContext ctx) {
        String userName=visitString(ctx.username);
        String password=visitString(ctx.password);;
        JCredentials credentials=new JCredentials();
        credentials.setUsername(JStringUtils.trim(userName));
        credentials.setPassword(JStringUtils.trim(password));
        return credentials;
    }


    @Override
    public Object visitLocationOption(JQuickCurlParser.LocationOptionContext ctx) {
        this.config.followRedirects(true) ;
        return true;
    }
    @Override
    public String visitDownloadOption(JQuickCurlParser.DownloadOptionContext ctx) {
        String fileName = visitString(ctx.string());
        this.downLoadFileName=fileName;
        return fileName;
    }
    public Object visitUploadOption(JQuickCurlParser.UploadOptionContext ctx) {
        if(null!=ctx.string()){
            String string = visitString(ctx.string());
            String uploadOption = JStringUtils.trim(string);
            if(StringUtils.isNotBlank(uploadOption)){
                String[] option=uploadOption.split("=");
                if(option.length==2){
                    JFormParam upLoadFile=new JFormParam();
                    upLoadFile.setKey(option[0]);
                    String formValue=option[1];
                    upLoadFile.setFile(formValue.startsWith("@"));
                    System.out.println(JStringUtils.trimJsonFormat(trimFileName(option[1])));
                    upLoadFile.setValue(JStringUtils.trimJsonFormat(trimFileName(option[1])));
                    this.upLoadFileList.add(upLoadFile);
                }else if(option.length==3) {
                    String up = JStringUtils.replaceJsonFormat(string);
                    String[] j=up.split("=");
                    JFormParam upLoadFile=new JFormParam();
                    upLoadFile.setKey(j[0]);
                    String formValue=j[1];
                    upLoadFile.setFile(formValue.startsWith("@"));
                    if(StringUtils.isNotEmpty(formValue)){
                        String str=formValue.replaceAll("\\\\\"","\"");
                        upLoadFile.setValue(str);
                    }
                    this.upLoadFileList.add(upLoadFile);
                }
            }
        }
        return null;
    }

    @Override
    public Object visitOtherOption(JQuickCurlParser.OtherOptionContext ctx) {
        return visitChildren(ctx);
    }
    @Override
    public String visitProxryOption(JQuickCurlParser.ProxryOptionContext ctx) {
        if(null!=ctx.string()){
            String proxryHost = visitString(ctx.string());
            if(StringUtils.isNotBlank(proxryHost)){
                String[] proxryAddr=proxryHost.trim().split(":");
                JProxryBean proxryBean=new JProxryBean();
                proxryBean.setType(JProxryType.HTTP);
                if(proxryAddr.length==2){
                    proxryBean.setHost(proxryAddr[0]);
                    proxryBean.setPort(Integer.parseInt(proxryAddr[1]));
                    this.jproxry=proxryBean;
                }else if(proxryAddr.length==1){
                    proxryBean.setHost(proxryAddr[0]);
                    proxryBean.setPort(80);
                    this.jproxry=proxryBean;
                }
            }
        }
        return null;
    }
    @Override
    public String visitSocketOption(JQuickCurlParser.SocketOptionContext ctx) {
        if(null!=ctx.string()){
            String proxryHost = visitString(ctx.string());
            if(StringUtils.isNotBlank(proxryHost)){
                String[] proxryAddr=proxryHost.trim().split(":");
                JProxryBean proxryBean=new JProxryBean();
                proxryBean.setType(JProxryType.HTTP);
                if(proxryAddr.length==2){
                    proxryBean.setHost(proxryAddr[0]);
                    proxryBean.setPort(Integer.parseInt(proxryAddr[1]));
                    this.jproxry=proxryBean;
                }else if(proxryAddr.length==1){
                    proxryBean.setHost(proxryAddr[0]);
                    proxryBean.setPort(80);
                    this.jproxry=proxryBean;
                }
            }
        }
        return null;
    }


    /**
     * PASS
     * @param ctx the parse tree
     * @return
     */
    @Override
    public String visitUrl(JQuickCurlParser.UrlContext ctx) {
        if(null!=ctx.string()){
            String url = visitString(ctx.string());
            return JStringUtils.trim(url);
        } else if (ctx.URL() != null) {
            String url = ctx.URL().getText();
            return JStringUtils.trim(url);
        }
        JAssert.throwNewException("invalid url");
        return url;
    }
    @Override
    public OkHttpClient visitHttp2Option(
            JQuickCurlParser.Http2OptionContext ctx) {
        OkHttpClient client = new OkHttpClient.Builder()
                .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))
                .build();
        return client;
    }
    @Override
    public Boolean visitIgnoreOption(JQuickCurlParser.IgnoreOptionContext ctx) {
        this.ignoreSsl=true;
        return this.ignoreSsl;
    }
    @Override
    public String visitVariable(JQuickCurlParser.VariableContext ctx) {
        String key=null;
        String v=ctx.getText();
        if(null!=ctx.IDENTIFIER()){
            key=JStringUtils.trim(ctx.IDENTIFIER().getText());
        }
        JAssert.notNull(key,"Variable key is null");
        Object value= this.context.get(key);
        JAssert.notNull(value,"Variable value is not null");
        return value.toString();//curl only support string
    }
    @Override
    public String visitString(JQuickCurlParser.StringContext ctx) {
        String value=null;
        if(null!=ctx.STRING()){
            value=JStringUtils.trim(ctx.STRING().getText());
            if(value.startsWith("${")&&value.endsWith("}")){
                JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(value));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                JQuickCurlParser parser = new JQuickCurlParser(tokens);
                ParseTree tree = parser.variable();
                JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(this.context);
                String result = (String)visitor.visit(tree);
                return result;
            }else{
                return value;
            }

        } else if (ctx.variable() != null) {
            return visitVariable(ctx.variable());
        } else if (ctx.IDENTIFIER() != null) {
            return JStringUtils.trim(ctx.IDENTIFIER().getText());
        }else if(ctx.UNQUOTED_CREDENTIALS() != null){
            return JStringUtils.trim(ctx.UNQUOTED_CREDENTIALS().getText());
        }else{
            return ctx.getText();
        }
    }


}
