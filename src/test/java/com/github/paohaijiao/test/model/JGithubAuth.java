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
package com.github.paohaijiao.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName com.github.paohaijiao.test.model
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/6
 */
@NoArgsConstructor
@Data
public class JGithubAuth {
    private String login;
    private int id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private String user_view_type;
    private boolean site_admin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private boolean hireable;
    private Object bio;
    private Object twitter_username;
    private String notification_email;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;
    private int private_gists;
    private int total_private_repos;
    private int owned_private_repos;
    private int disk_usage;
    private int collaborators;
    private boolean two_factor_authentication;
    private PlanBean plan;

    @NoArgsConstructor
    @Data
    public static class PlanBean {
        private String name;
        private int space;
        private int collaborators;
        private int private_repos;
    }
}
