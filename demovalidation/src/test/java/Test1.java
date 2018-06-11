
import org.junit.Test;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;


public class Test1 {

  @Test
    public void jsonValidationShouldNotWork() throws Exception {

        Parser parser = new Parser();
        parser.run(new File("src\\main\\resources\\schema.json"));
        assertTrue(false);

    }

}

