package br.com.alura.Forumhub.dto.response;

public record TopicListActiveDto(Long id,
                                 String title,
                                 String message,
                                 UserIdDto author,
                                 CourseIdDto course) {

    public TopicListActiveDto(Long id, String title, String message, UserIdDto author, CourseIdDto course) {
        this.id = id;
        this.title = title();
        this.message = message();
        this.author = author();
        this.course = course();
    }
}
