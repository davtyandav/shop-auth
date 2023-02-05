package com.davtyan.sequrity.sequrityapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SequrityApiApplicationTests {

    @Test
    void contextLoads() {

        List<Integer> integerList = List.of(1, 3, 5, 6, 8, 9);
        List<Integer> integerList1 = List.of(2, 3, 6, 9, 8);


        for (Integer integer1 : integerList1) {
            for (Integer integer : integerList) {

                if (!integer1.equals(integer)) {
                    System.out.println(integer);
                }
            }
        }

    }

}
