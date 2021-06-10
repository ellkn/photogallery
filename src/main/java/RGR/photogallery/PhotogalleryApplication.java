package RGR.photogallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class PhotogalleryApplication {
	public static void main(String[] args) {
		SpringApplication.run(PhotogalleryApplication.class, args);
	}

}
