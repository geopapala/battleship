package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.InputHandler;

class InputHandlerTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void testAskPlayersForNames() {
        String input = "Anya\nBeth\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        InputHandler inputHandler = new InputHandler(scanner);
        String[] names = inputHandler.askPlayersForNames();

        assertEquals("Anya", names[0]);
        assertEquals("Beth", names[1]);
    }

    @Test
    public void testAskPlayersForNamesWithDuplicate() {
        String input = "Anya\nAnya\nBeth\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        InputHandler inputHandler = new InputHandler(scanner);
        String[] names = inputHandler.askPlayersForNames();

        assertEquals("Anya", names[0]);
        assertEquals("Beth", names[1]);
    }
    
    @Test
    public void testAskPlayersForNamesWithEmptyTheFirstName() {
        String input = "\nBeth\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        InputHandler inputHandler = new InputHandler(scanner);
        String[] names = inputHandler.askPlayersForNames();

        assertEquals("A", names[0]);
        assertEquals("Beth", names[1]);
    }
    
    @Test
    public void testAskPlayersForNamesWithEmptyTheSecondName() {
        String input = "Anya\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        InputHandler inputHandler = new InputHandler(scanner);
        String[] names = inputHandler.askPlayersForNames();

        assertEquals("Anya", names[0]);
        assertEquals("B", names[1]);
    }
    
    @Test
    public void testAskPlayersForNamesWithBothNamesEmpty() {
        String input = "\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        InputHandler inputHandler = new InputHandler(scanner);
        String[] names = inputHandler.askPlayersForNames();

        assertEquals("A", names[0]);
        assertEquals("B", names[1]);
    }
}
