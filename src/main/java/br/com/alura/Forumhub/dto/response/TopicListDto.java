package br.com.alura.Forumhub.dto.response;

public record TopicListDto( Long id,
                            String title,
                            String message,
                            UserIdDto author,
                            CourseIdDto course,
                            boolean status) {
    public TopicListDto(Long id, String title, String message, UserIdDto author, CourseIdDto course, boolean status) {
        this.id = id;
        this.title = title();
        this.message = message();
        this.author = author();
        this.course = course();
        this.status = status;
    }
}
