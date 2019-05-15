package cyk;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
public class CYK_Classes_teszt {
    CYK_Classes teszt;
    @BeforeEach
    public void setUp(){teszt = new CYK_Classes();}
    @AfterEach
    public void tearDown(){ teszt = null;}
    @Test
    public void komb_test(){
        assertArrayEquals(new String[]{"SS", "SB","AS","AB","BS","BB","CS","CB"}, teszt.komb("[S,A,B,C]","[S,B]"));
    }
    @Test
    public void komb_test_2(){
        assertArrayEquals(new String[]{"SS","SA","SC","BS","BA","BC","DS","DA","DC"},teszt.komb("[S,B,D]","[S,A,C]"));
    }
    @Test
    public void elsofeltolt_test(){
        assertArrayEquals(new String[][]{{"[S]","[A]","[B]"},{null,null,null},{null,null,null}},teszt.elsofeltolt("abc",new String[][]{{"S","SA|a"},{"A","AB|b"},{"B","c"}}));
    }
    @Test
    public void  Elsofeltolt_test_2(){
        assertArrayEquals(new String[][]{{"[B]","[A]","[C]","[S]"},{null,null,null,null},{null,null,null,null},{null,null,null,null}},teszt.elsofeltolt("adcb",new String[][]{{"S","AS|b"},{"A","BC|d"},{"B","C|a"},{"C","c"}}));
    }
    @Test
    public void feltolt_test(){
        assertArrayEquals(new String[][]{{"[B]", "[A]", "[S]", "[A]", "[C]"},
        {"[]", "[]", "[]", "[B]", null},
            {"[]", "[]", "[]", null, null},
                {"[]", "[]", null, null, null},
                    {"[]", null, null, null, null}},teszt.feltolt(new String[][]{{"[B]", "[A]", "[S]", "[A]", "[C]"},
                {"[]", "[]", "[]", "[B]", null},
                {"[]", "[]", "[]", null, null},
                {"[]", "[]", null, null, null},
                {"[]", null, null, null, null}},"abcbd",new String[][]{{"S","BS|c"},{"A","CB|b"},{"B","AC|a"},{"C","d"}}));
    }
    @Test
    public void feltolt_test_2(){
        assertArrayEquals(new String[][]{{"[C]", "[B]", "[S]", "[A]", "[A]"},
                {"[B]", "[A]", "[]", "[]", null},
                {"[A]", "[]", "[]", null, null},
                {"[]", "[]", null, null, null},
                {"[]", null, null, null, null}},teszt.feltolt(new String[][]{{"[C]", "[B]", "[S]", "[A]", "[A]"},
                {"[]", "[]", "[]", "[B]", null},
                {"[]", "[]", "[]", null, null},
                {"[]", "[]", null, null, null},
                {"[]", null, null, null, null}},"bcdaa",new String[][]{{"S","BA|d"},{"A","BS|a"},{"B","CB|c"},{"C","SC|b"}}));
    }
}
