package br.com.alura.Forumhub.dto.response;

import br.com.alura.Forumhub.dto.request.ResponseDto;

import java.util.List;

public record TopicDetailsDto(Long id,
                              String title,
                              String message,
                              UserIdDto author,
                              CourseIdDto course,
                              List<ResponseDto> response,
                              boolean status) {

    public TopicDetailsDto(Long id, String title, String message, UserIdDto author, CourseIdDto course, List<ResponseDto> response, boolean status) {
        this.id = id;
        this.title = title();
        this.message = message();
        this.author = author();
        this.course = course();
        this.response = response();
        this.status = status;
    }


}
