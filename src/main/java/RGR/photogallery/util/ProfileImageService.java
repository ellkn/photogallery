package RGR.photogallery.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class ProfileImageService {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    public static String BIG_AVATAR_POSTFIX = "_big_thumb.png";
    public static String SMALL_AVATAR_POSTFIX = "_small_thumb.png";

    @Value("${project.profile.image.dir.path}")
    private String profileImgDirPath;

    public FileSystemResource getImage(Long id, String postfix) {
        String avatarFileName = profileImgDirPath + File.separator + id + File.separator + id + postfix;

        File f = new File(avatarFileName);
        if (f.exists() && !f.isDirectory()) {
            return new FileSystemResource(f);
        } else {
            try {
                f = new ClassPathResource("/static/img/profile" + postfix).getFile();
                if (f.exists() && !f.isDirectory()) {
                    return new FileSystemResource(f);
                }
            } catch (IOException e) {
                logger.severe(e.getMessage());
            }
        }

        return null;
    }

    public boolean saveCarImage(MultipartFile multipartFile, Long userId) {
        boolean result = true;
        String filePath = profileImgDirPath + File.separator + userId + File.separator;

        if (!(new File(filePath).exists())) {
            new File(filePath).mkdirs();
        }

        try {
            FileUtils.cleanDirectory(new File(filePath));

            String orgName = multipartFile.getOriginalFilename();
            String fullFilePath = filePath + orgName;

            File dest = new File(fullFilePath);
            multipartFile.transferTo(dest);

            Thumbnails.of(dest).size(80, 80).crop(Positions.CENTER)
                    .toFile(new File(filePath + userId + BIG_AVATAR_POSTFIX));
            Thumbnails.of(dest).size(35, 35).crop(Positions.CENTER)
                    .toFile(new File(filePath + userId + SMALL_AVATAR_POSTFIX));

        } catch (IllegalStateException e) {
            logger.severe(e.getMessage());
            result = false;
        } catch (IOException e) {
            logger.severe(e.getMessage());
            result = false;
        }

        return result;
    }
}
