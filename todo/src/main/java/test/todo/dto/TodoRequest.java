package test.todo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class TodoRequest {
	
	@Getter @Setter
	public static class CreateRequest{
		@NotBlank(message = "이름은 필수입니다.")
		private String name;
	}

	@Getter @Setter
	public static class UpdateRequest{
		private String name;
		private Boolean completed;
	}
	
	@Setter
	public static class Search{
		@Max(value = 100)
		private Integer limit;
		private Integer skip;
		
		public Integer getLimit() {
			if(this.limit != null) {
				return this.limit;
			}
			return 10;
		}
		
		public Integer getSkip() {
			if(this.skip != null) {
				return this.skip;
			}
			return 0;
		}
	}
}
