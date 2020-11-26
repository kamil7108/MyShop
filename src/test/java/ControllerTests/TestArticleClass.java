package ControllerTests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.polsl.lab1.shop.Model.Article;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestArticleClass {
    private static Stream<Arguments> generator() {
    return Stream.of(
            Arguments.of(new Article("NAME",0.0,"TEST"),4),
            Arguments.of(new Article("NAME1",0.0,"TEST2"),5),
            Arguments.of(new Article("",0.0,""),6));
    }

    @ParameterizedTest
    @MethodSource("generator")
    public void test_toString_correct_format(Article SUT){
        assertEquals(SUT.getName()+" "+SUT.getPrice()+"$",SUT.toString());
    }

    @ParameterizedTest
    @MethodSource("generator")
    public void test_check_auto_increment_id_of_article(Article SUT,int id)
    {
      assertEquals(id,SUT.getId());
    }

}
