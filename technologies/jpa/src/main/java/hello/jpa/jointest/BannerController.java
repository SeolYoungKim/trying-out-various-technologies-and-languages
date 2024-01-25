package hello.jpa.jointest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/banners")
    public String banners() {
        List<Banner> allBanner = bannerService.findAll();

        StringBuilder stringBuilder = new StringBuilder();
        allBanner.forEach(banner -> {
            stringBuilder.append(banner.getName());
            stringBuilder.append(" ");
            stringBuilder.append(banner.getChannel().getName());
            stringBuilder.append(" ");
            stringBuilder.append(banner.getGenre().getName());
            stringBuilder.append("\n");
        });

        return stringBuilder.toString();
    }
}
