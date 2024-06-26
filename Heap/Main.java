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
        heapSort(arrayToSort, arrayToSort.length);
        for(int k =0; k < arrayToSort.length -1; k++){
            if(arrayToSort[k] != null) {
                System.out.println(arrayToSort[k].imprimirDados());
            }
        }
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
    public static void heapSort(Acomodacao[] array, int n) {

        // Criando outro vetor, com todos os elementos do vetor anterior reposicionados (uma posição a frente)
        // de forma a ignorar a posição zero
        Acomodacao[] tmp = new Acomodacao[n + 1];
        for(int i = 0; i < n; i++) {
            tmp[i+1] = array[i];
        }

        //Contrução do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++) {
            constroi(tmp, tamHeap);
        }

        //Ordenação propriamente dita
        int tamHeap = n;
        while(tamHeap > 1) {
            troca(tmp, 1, tamHeap--);
            reconstroi(tmp, tamHeap);
        }

        //Alterar o vetor para voltar à posição zero
        for(int i = 0; i < n; i++) {
            array[i] = tmp[i+1];
        }
    }

    static void constroi(Acomodacao[] array, int tamHeap) {

        for(int i = tamHeap; i > 1 && verifica(array[i],array[i/2]); i /= 2) {
            troca(array, i, i/2);
        }
    }

    public static boolean verifica(Acomodacao a1, Acomodacao a2){
        if(a1 == null || a2 == null){
            return false;
        }else{
            if(a1.getReviews() == a2.getReviews()){
                return a1.getRoomId() > a2.getRoomId();
            }
        }
        return false;
    }


    static void reconstroi(Acomodacao[] array, int tamHeap) {

        int i = 1;

        while(i <= (tamHeap/2)) {
            int filho = getMaiorFilho(array, i, tamHeap);
            if(verifica(array[i],array[filho])) {
                troca(array, i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    static int getMaiorFilho(Acomodacao[] array, int i, int tamHeap) {

        int filho;

        if (2*i == tamHeap || verifica(array[2*i],array[2*i*1])){
            filho = 2*i;
        } else {
            filho = 2*i + 1;
        }
        return filho;
    }
    static void troca(Acomodacao[] array, int i, int j) {

        Acomodacao temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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