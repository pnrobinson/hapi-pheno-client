package org.monarchinitiative.hapiphenoclient.ga4gh;

import org.monarchinitiative.hapiphenoclient.phenopacket.Individual;
import org.monarchinitiative.hapiphenoclient.phenopacket.Measurement;
import org.monarchinitiative.hapiphenoclient.phenopacket.PhenotypicFeature;
import org.phenopackets.schema.v2.Phenopacket;

import java.util.ArrayList;
import java.util.List;

public class Ga4GhPhenopacket {


    public static Phenopacket fromFhir(Individual patient, List<PhenotypicFeature> features, List<Measurement> measurements) {
        Phenopacket.Builder builder = Phenopacket.newBuilder();
        org.phenopackets.schema.v2.core.Individual ga4ghIndividual = IndividualTransformer.toGa4gh(patient);
        builder.setSubject(ga4ghIndividual);
        List<org.phenopackets.schema.v2.core.PhenotypicFeature> ga4ghFeatures =  new ArrayList<>();
        for (PhenotypicFeature pf : features) {
            org.phenopackets.schema.v2.core.PhenotypicFeature ga4ghPf = PhenotypicFeatureTransformer.toGa4gh(pf);
            ga4ghFeatures.add(ga4ghPf);
        }
        builder.addAllPhenotypicFeatures(ga4ghFeatures);
        List<org.phenopackets.schema.v2.core.Measurement> ga4ghMeasures = new ArrayList<>();
        for (Measurement fhirMeasurement : measurements) {
            org.phenopackets.schema.v2.core.Measurement ga4ghMeasurement = MeasurementTransformer.toGa4gh(fhirMeasurement);
            ga4ghMeasures.add(ga4ghMeasurement);
        }
        builder.addAllMeasurements(ga4ghMeasures);
        return builder.build();
    }


}
