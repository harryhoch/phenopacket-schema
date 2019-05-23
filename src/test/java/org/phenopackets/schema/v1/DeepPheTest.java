package org.phenopackets.schema.v1;

import org.junit.jupiter.api.Test;
import org.phenopackets.schema.v1.core.*;
import org.phenopackets.schema.v1.examples.TestExamples;
import org.phenopackets.schema.v1.io.PhenopacketFormat;

import java.io.IOException;

import static org.phenopackets.schema.v1.PhenoPacketTestUtil.ontologyClass;

/**
 * @author Jules Jacobsen <j.jacobsen@qmul.ac.uk>
 */
public class DeepPheTest {

    @Test
    public void testDeepPhe() throws IOException {
        System.out.println(PhenopacketFormat.toYaml(TestExamples.deepPhePhenopacket()));
    }
}
