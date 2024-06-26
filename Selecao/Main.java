import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File arquivo = new File("/tmp/dados_airbnb.txt");

        LineNumberReader lnr = new LineNumberReader(new FileReader(arquivo));
        lnr.skip(Long.MAX_VALUE);
        int N = lnr.getLineNumber();
        Acomodacao[] ArrayAcomodacao = new Acomodacao[N];

        BufferedReader fileReader = new BufferedReader(new FileReader(arquivo));

        String linha = fileReader.readLine();
        linha = fileReader.readLine();
            int count = 0;
             while (linha != null) {
                ArrayAcomodacao[count] = new Acomodacao(linha.split("\t"));
                count++;
                 linha = fileReader.readLine();
            }
            fileReader.close();

        Scanner sc = new Scanner(System.in);
        int entrada = sc.nextInt();
        Acomodacao[] arrayToSort = new Acomodacao[entrada];
        int roomId = sc.nextInt();

        for(int i = 0; i < entrada - 1; i++){
            MontarArray(ArrayAcomodacao,roomId, arrayToSort);
            roomId = sc.nextInt();
        }
        sc.close();
        selectionsort(arrayToSort);
        for(int k =0; k < arrayToSort.length -1; k++){
            System.out.println(arrayToSort[k].imprimirDados());
        }
        System.out.println(" ");

    }

    public static void MontarArray(Acomodacao[] arr, int id, Acomodacao[] arrToSort){
        Acomodacao acc = FindRoomById(arr,id);
        if(acc != null){
            for(int i = 0; i < arrToSort.length; i++){
                if(arrToSort[i] == null){
                    arrToSort[i] = acc;
                    break;
                }
            }
        }
    }


    public static void selectionsort(Acomodacao[] acomodacoes){
        for (int i = 0; i < acomodacoes.length - 1; i++) {
            int smallestIndex = i;

            for (int j = i + 1; j < acomodacoes.length - 1 ; j++) {

                if (acomodacoes[j].getCountry().compareTo(acomodacoes[smallestIndex].getCountry()) < 0) {
                    smallestIndex = j;

                } else if (acomodacoes[j].getCountry().compareTo(acomodacoes[smallestIndex].getCountry()) == 0) {

                    if (acomodacoes[j].getCity().compareTo(acomodacoes[smallestIndex].getCity()) < 0) {
                        smallestIndex = j;

                    } else if (acomodacoes[j].getCity().compareTo(acomodacoes[smallestIndex].getCity()) == 0) {

                        if (acomodacoes[j].getNeighborhood().compareTo(acomodacoes[smallestIndex].getNeighborhood()) < 0) {
                            smallestIndex = j;

                        } else if (acomodacoes[j].getNeighborhood().compareTo(acomodacoes[smallestIndex].getNeighborhood()) == 0) {

                            if (acomodacoes[j].getRoomId() < acomodacoes[smallestIndex].getRoomId()) {
                                smallestIndex = j;
                            }
                        }
                    }
                }
            }
            swap(acomodacoes, i, smallestIndex);
        }
    }


    private static void swap(Acomodacao[] acomodacoes, int indexA, int indexB) {
        var aux = acomodacoes[indexA];
        acomodacoes[indexA] = acomodacoes[indexB];
        acomodacoes[indexB] = aux;
    }

    public static Acomodacao FindRoomById(Acomodacao[] acomodacoes, int id){
        for(int i = 0; i < acomodacoes.length; i++){
            if(acomodacoes[i].getRoomId() == id){
                return  acomodacoes[i];
            }
        }
        return null;
    }

    public static class Acomodacao implements Cloneable  {

        private int roomId;
        private int hostId;
        private String roomType;
        private String country;
        private String city;
        private String neighborhood;
        private int reviews;
        private double overallSatisfaction;
        private int accommodates;
        private double bedrooms;
        private double price;
        private String propertyType;

        public Acomodacao(String[] campos) {
            this.roomId = Integer.parseInt(campos[0]);
            this.hostId = Integer.parseInt(campos[1]);
            this.roomType = campos[2];
            this.country = campos[3];
            this.city = campos[4];
            this.neighborhood = campos[5];
            this.reviews = Integer.parseInt(campos[6]);
            this.overallSatisfaction = Double.parseDouble(campos[7]);
            this.accommodates = Integer.parseInt(campos[8]);
            this.bedrooms = Double.parseDouble(campos[9]);
            this.price = Double.parseDouble(campos[10]);
            this.propertyType = campos[11];
        }
        public Acomodacao() {
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getHostId() {
            return hostId;
        }

        public void setHostId(int hostId) {
            this.hostId = hostId;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getNeighborhood() {
            return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
        }

        public int getReviews() {
            return reviews;
        }

        public void setReviews(int reviews) {
            this.reviews = reviews;
        }

        public double getOverallSatisfaction() {
            return overallSatisfaction;
        }

        public void setOverallSatisfaction(double overallSatisfaction) {
            this.overallSatisfaction = overallSatisfaction;
        }

        public int getAccommodates() {
            return accommodates;
        }

        public void setAccommodates(int accommodates) {
            this.accommodates = accommodates;
        }

        public double getBedrooms() {
            return bedrooms;
        }

        public void setBedrooms(double bedrooms) {
            this.bedrooms = bedrooms;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(String propertyType) {
            this.propertyType = propertyType;
        }

        public int getRoomId() {
            return roomId;
        }

        @Override
        public String toString() {
            return "Acomodacao{}";
        }

        public String imprimirDados() {
            return "[" + roomId + " ## " + hostId + " ## " + roomType + " ## " + country + " ## " + city + " ## " +
                    neighborhood + " ## " + reviews + " ## " + overallSatisfaction + " ## " + accommodates + " ## " +
                    bedrooms + " ## " + price + " ## " + propertyType + "]";
        }

        @Override
        public Acomodacao clone() {
            try {
                Acomodacao clone = (Acomodacao) super.clone();
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}