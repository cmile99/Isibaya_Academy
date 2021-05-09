package com.threeklines.isibayaacademyclient;

public class Topic {
    private final String subject;
    private final String topic;
    private final String topicDesc;

    public Topic(String subject, String topic, String topicDesc) {
        this.subject = subject;
        this.topic = topic;
        this.topicDesc = topicDesc;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public String getTopicDesc() {
        return topicDesc;
    }
}
