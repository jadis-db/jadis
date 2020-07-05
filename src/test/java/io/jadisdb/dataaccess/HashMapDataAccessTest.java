package io.jadisdb.dataaccess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashMapDataAccessTest {
    @Test
    public void get_test() {
        DataAccess dataAccess = new HashMapDataAccess();

        // 값이 없는 경우에는 null을 리턴한다.
        assertNull(dataAccess.get("1"));


        // 값이 있는 경우에는 값을 가지고 온댜.
        dataAccess.put("1", new TextNode("Test"));
        assertEquals(dataAccess.get("1"), new TextNode("Test"));
    }

    @Test
    public void put_test() {
        DataAccess dataAccess = new HashMapDataAccess();

        // put하고 리턴된 결과값은 입력된 결과값이랑 동일하다.
        JsonNode jsonNode = dataAccess.put("1", new TextNode("Test"));
        assertEquals(jsonNode, new TextNode("Test"));
    }


    @Test
    public void remove_test() {
        DataAccess dataAccess = new HashMapDataAccess();
        dataAccess.put("1", new TextNode("Test"));

        // delete후 리턴된 결과값은 기존에 저장된 결과값이랑 동일하다.
        assertEquals(dataAccess.remove("1"), new TextNode("Test"));
    }

}