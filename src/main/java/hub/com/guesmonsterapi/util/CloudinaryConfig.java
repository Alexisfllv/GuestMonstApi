package hub.com.guesmonsterapi.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dfofbqqlg",
                "api_key", "648263753269416",
                "api_secret", "vMddMfYmckW_ja-wO8R3_qnZm7I",
                "secure", true
        ));

    }
}
