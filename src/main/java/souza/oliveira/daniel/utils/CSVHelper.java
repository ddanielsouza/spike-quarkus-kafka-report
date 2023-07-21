package souza.oliveira.daniel.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import souza.oliveira.daniel.dto.OpportunityDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static ByteArrayInputStream opportunitiesToCSV(List<OpportunityDTO> opportunities){
        final var format = CSVFormat
                .DEFAULT
                .withHeader("ID Proposta", "Cliente", "Preço por tonelada", "Melhor cotação de moeda");

        try(
            final var out = new ByteArrayOutputStream();
            final var csvPrinter = new CSVPrinter(new PrintWriter(out), format);){

            for(OpportunityDTO opp: opportunities){
                List<String> data = Arrays.asList(
                        String.valueOf(opp.getProposalId()),
                        opp.getCustomer(),
                        String.valueOf(opp.getPriceTonne()),
                        String.valueOf(opp.getLastDollarQuotation()));

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();

            return new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e){
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage(), e);
        }
    }
}
