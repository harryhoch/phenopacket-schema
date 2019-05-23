package org.phenopackets.schema.v1.examples;

import com.google.protobuf.Timestamp;
import org.phenopackets.schema.v1.Phenopacket;
import org.phenopackets.schema.v1.core.*;

import java.time.Instant;

import static org.phenopackets.schema.v1.PhenoPacketTestUtil.ontologyClass;


/**
 * Phenopacket representation of the cancer example from the Toronto hackathon. See
 * src/test/resources/toronto_cancer_example.md.
 *
 * @author Jules Jacobsen <j.jacobsen@qmul.ac.uk>
 */
class DeepPhePhenopacketExample {

    static Phenopacket deepPhePhenopacket() {
        MetaData metaData = MetaData.newBuilder()
                .addResources(Resource.newBuilder()
                        .setId("ncit")
                        .setName("NCI Thesaurus OBO Edition")
                        .setNamespacePrefix("NCIT")
                        .setUrl("http://purl.obolibrary.org/obo/ncit.owl")
                        .setVersion("18.05d")
                        .build())
                .build();

        String patientId = "patient1";

        //Diagnosis - should this be under Disease, or is it a phenotype of the patient or the biosample?
        Disease esophagealCarcinoma = Disease.newBuilder()
                .setTerm(ontologyClass("NCIT:C4024","Esophageal Squamous Cell Carcinoma"))
                .build();


        Individual patient = Individual.newBuilder()
                .setId(patientId)
                .setDateOfBirth(Timestamp.newBuilder()
                        .setSeconds(Instant.parse("1964-03-15T00:00:00Z").getEpochSecond()))
                .build();

        HgvsAllele allele = HgvsAllele.newBuilder()
                .setId("NG_007503.1:g.41608G>A")
                .build();

        Variant  variant = Variant.newBuilder()
                .setHgvsAllele(allele)
                .setZygosity(ontologyClass("GENO:0000136", "homozygous"))
                .build();

         CancerBioMarker biomarker = CancerBioMarker.newBuilder()
                 .setVariant(variant)
                 .setInterpretation("HER2 positive")
                 .build();

         Treatment treatment = Treatment.newBuilder()
                 .setTreatment(ontologyClass("NCIT:C62078","Tamoxifen"))
                 .setDescription("Tamoxifen")
                 .build();


        Tumor tumor =  Tumor.newBuilder()
                .setLocation(ontologyClass("NCT:C47855","Left Breast"))
                .addBiomarkers(biomarker)
                .build();

        Cancer cancer = Cancer.newBuilder()
                .setDiagnosis(esophagealCarcinoma)
                .addTumors(tumor)
                .addTreatments(treatment)
                .build();


        return Phenopacket.newBuilder()
                .setSubject(patient)
                .addCancers(cancer)
                .setMetaData(metaData)
                .build();
    }
}
