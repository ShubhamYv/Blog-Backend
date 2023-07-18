package com.sky.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	@NotEmpty
	@Size(min = 4, message = "Post title must be minimum of 4 characters")
	private String title;
	@NotEmpty
	@Size(min = 4, message = "Post content must be minimum of 6 characters")
	private String content;
	@NotEmpty
	private String imageName;
	@NotEmpty
	private Date addedDate;
	@NotEmpty
	private CategoryDto category;
	@NotEmpty
	private UserDto user;
}
