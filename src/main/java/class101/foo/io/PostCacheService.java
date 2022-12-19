package class101.foo.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PostCacheService {
    @Autowired
    PostRepository postRepository;
    private Page<Post> firstPostPage;

    @Scheduled(cron = "* * * * * *")        // 1초에 한 번씩 아래 메서드가 실행된다.
    public void updateFirstPostPage(){      // firstPostPage에 1초마다 최신의 1페이지 정보가 갱신된다.
         firstPostPage = postRepository.findAll(
                PageRequest.of(0, 20, Sort.by("id").descending())
        );
    }

    public Page<Post> getFirstPostPage(){
        return this.firstPostPage;
    }
}
