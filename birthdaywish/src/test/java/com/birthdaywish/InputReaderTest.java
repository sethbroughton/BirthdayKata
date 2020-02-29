package com.birthdaywish;


import java.io.FileNotFoundException;
import java.util.List;

import org.junit.*;

public class InputReaderTest {
	
@Test
public void test_valid_input_from_txt_input_file() throws FileNotFoundException {
	InputReader input = new InputReader();
	List<Friend> friends = input.readFile();
	
}


}
