package hello.jpa.isnew;

import hello.jpa.isnew.generated.GeneratedItem;
import hello.jpa.isnew.generated.GeneratedItemRepository;
import hello.jpa.isnew.non_generated.NonGeneratedItem;
import hello.jpa.isnew.non_generated.NonGeneratedItemRepository;
import hello.jpa.isnew.non_generated.PersistableImpl;
import hello.jpa.isnew.non_generated.PersistableImplRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IsNewTest {
    @Autowired
    GeneratedItemRepository generatedRepo;

    @Autowired
    NonGeneratedItemRepository nonGeneratedRepo;

    @Autowired
    PersistableImplRepository persistableImplRepository;

    @Test
    void generatedIdTest() {
        final GeneratedItem generatedItem = new GeneratedItem();
        generatedRepo.save(generatedItem);  // ID가 없으면 insert, 있으면 select->update로 동작
    }

    @Test
    void nonGeneratedIdTest() {
        final NonGeneratedItem nonGeneratedItem = new NonGeneratedItem("UUID");
        nonGeneratedRepo.save(nonGeneratedItem);  // ID가 있기 때문에 merge로 동작 해버림...
        // 변경은 변경 감지를 사용해야함(merge는 거의 안씀. select 쿼리를 날린 후 insert/update 쿼리를 날리기 때문에 비효율적임)
    }

    @Test
    void persistableImplementation() {
        final PersistableImpl persistableImpl = new PersistableImpl("UUID");
        persistableImplRepository.save(persistableImpl);  // isNew를 구현 해주었으므로 persist! (insert)
    }
}