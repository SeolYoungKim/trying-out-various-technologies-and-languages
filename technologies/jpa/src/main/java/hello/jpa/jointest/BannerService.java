package hello.jpa.jointest;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class BannerService {
    private final BannerJpaRepository bannerJpaRepository;

    public BannerService(BannerJpaRepository bannerJpaRepository) {
        this.bannerJpaRepository = bannerJpaRepository;
    }

    public List<Banner> findAll() {
        return bannerJpaRepository.findAll();
    }

    @PostConstruct
    void init() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Banner banner = new Banner();
            banner.setName("banner" + i);

            BannerChannel channel = new BannerChannel();
            channel.setName("channel" + i);
            banner.setChannel(channel);

            BannerGenre genre = new BannerGenre();
            genre.setName("genre" + i);
            banner.setGenre(genre);

            bannerJpaRepository.save(banner);
        });
    }
}
