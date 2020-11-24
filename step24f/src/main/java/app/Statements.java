package app;

import app.entity.Post;
import app.entity.Tag;
import app.repo.PostRepo;
import app.repo.TagRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Statements {

  private final PostRepo postRepo;
  private final TagRepo tagRepo;
  private final EntityManager em;

  /**
   * entities without relations
   * IDs must be null !!!
   */
//  @Bean
  public CommandLineRunner example1_no_relation() {
    return args -> {
      /** 1. create tags */
      List<Tag> tags = tagRepo.saveAll(Arrays.asList(
          new Tag("Java"),
          new Tag("Scala"),
          new Tag("Haskell")
      ));
      tags.forEach(System.out::println);
      /** create posts */
      List<Post> posts = postRepo.saveAll(Arrays.asList(
          new Post("Basic"),
          new Post("Advanced"),
          new Post("Nerd")
      ));
      posts.forEach(System.out::println);
      /** attach */
    };
  }

  /**
   * entities WITH relations
   * IDs must be null !!!
   * hibernate will manage to do everything
   */
//  @Bean
  public CommandLineRunner example2_with_relations() {
    return args -> {
      // create tags
      Tag t1 = new Tag("Java");
      Tag t2 = new Tag("Scala");
      Tag t3 = new Tag("Haskell");
      // create lists of tags
      List<Tag> t123 = Arrays.asList(t1, t2, t3);
      List<Tag> t12 = Arrays.asList(t1, t2);
      List<Tag> t23 = Arrays.asList(t2, t3);
      // create posts
      Post p1 = new Post("Basic", t123);
      Post p2 = new Post("Advanced", t12);
      Post p3 = new Post("Nerd", t23);
      // persist them
      postRepo.saveAll(Arrays.asList(p1,p2,p3));
    };
  }

  @Bean
  public CommandLineRunner example3_mix() {
    return new CommandLineRunner() {
      @Override
      @Transactional          // <----- crucial because of detached entity
      public void run(String... args) {
        List<Tag> tt123 = tagRepo.findAllById(Arrays.asList(1, 2, 3));
        Post p4 = new Post("Extreme", tt123);
        postRepo.saveAll(Arrays.asList(p4));
      }};
  }

}
