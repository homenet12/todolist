package test.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class TodoRequest {
	
	@Getter @Setter
	public static class createRequest{
		@NotBlank(message = "이름은 필수입니다.")
		private String name;
	}

	@Getter @Setter
	public static class updateRequest{
		@NotNull(message = "id는 필수입니다.")
		private Long id;
		private String name;
		private Boolean completed;
	}
}
