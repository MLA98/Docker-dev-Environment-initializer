package com.neo.util;

public class UserConfig {
    private String OS;
    private String containerPort;
    private String hostPort;
    private String path;
    private String unconfined;

    public String getUnconfined() {
        return unconfined;
    }

    public void setUnconfined(String unconfined) {
        this.unconfined = unconfined;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getContainerPort() {
        return containerPort;
    }

    public void setContainerPort(String containerPort) {
        this.containerPort = containerPort;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "OS='" + OS + '\'' +
                ", containerPort='" + containerPort + '\'' +
                ", hostPort='" + hostPort + '\'' +
                ", path='" + path + '\'' +
                ", unconfined=" + unconfined +
                '}';
    }
}