package test.todo.common;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileHelper {
	
	public final static String uploadPath = "D:/fileupload";
	private final static String[] imgPrefix = {"jpg", "png", "gif"};

	public static String imgUpload(MultipartFile multipartFile) {
		
		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
		if(!Arrays.asList(imgPrefix).contains(prefix)) {
			throw new IllegalArgumentException("file is not img");
		}
		String filename = UUID.randomUUID().toString() + "." + prefix;
		
		File folder = new File(uploadPath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		String realPath = uploadPath+"/"+filename;
		File uploadFile = new File(realPath);
		try {
			multipartFile.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return realPath;
	}
}
