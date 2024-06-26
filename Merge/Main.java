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
        mergesort(arrayToSort, 0, arrayToSort.length - 1);
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

    private static void mergesort(Acomodacao[] array, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(array, esq, meio);
            mergesort(array, meio + 1, dir);
            intercalar(array, esq, meio, dir);
        }
    }

    private static void intercalar(Acomodacao[] array, int esq, int meio, int dir) {

        int n1, n2, i, j, k;

        //Definir tamanho dos dois subarrays
        n1 = meio - esq + 1;
        n2 = dir - meio;

        Acomodacao[] a1 = new Acomodacao[n1];
        Acomodacao[] a2 = new Acomodacao[n2];

        //Inicializar primeiro subarray
        for (i = 0; i < n1; i++) {
            a1[i] = array[esq + i];
        }

        //Inicializar segundo subarray
        for (j = 0; j < n2; j++) {
            a2[j] = array[meio + j + 1];
        }

        //Intercalação propriamente dita
        for (i = j = 0, k = esq; (i < n1 && j < n2); k++) {
            if(a2[j] != null) {
                if (a1[i].hostId == a2[j].hostId) {
                    if (a1[i].roomId > a2[j].roomId) {
                        array[k] = a2[j++];
                    } else {
                        array[k] = a1[i++];
                    }
                } else if (a1[i].hostId < a2[j].hostId) {
                    array[k] = a1[i++];
                } else {
                    array[k] = a2[j++];
                }
            }else{
                j++;
            }
        }

        if (i == n1)
            for (; k <= dir; k++) {
                array[k] = a2[j++];
            }
        else
            for (; k <= dir; k++) {
                array[k] = a1[i++];
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