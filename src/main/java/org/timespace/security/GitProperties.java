package org.timespace.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@ConfigurationProperties("git")
public class GitProperties {
    private final Github github = new Github();

    private final Security security = new Security();

    public Github getGithub() {
        return this.github;
    }

    public Security getSecurity() {
        return this.security;
    }


    public static class Github {

        private String token;

        public String getToken() {
            return this.token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class Security {

        private List<String> admins = Collections.singletonList("miniplugin");//XXXXX 자리에 여러분의 github 이름을 입력하시면 관리자로 인증됩니다.

        public List<String> getAdmins() {
            return admins;
        }

        public void setAdmins(List<String> admins) {
            this.admins = admins;
        }

    }
}