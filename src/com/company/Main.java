package com.company;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputPath = "C:\\Users\\Airis\\darbo_failai\\algo\\c1559_4_2022.tsv";
        String outputPath = "C:\\Users\\Airis\\darbo_failai\\algo\\result.tsv";

        List<Customer> customerList = readFile(inputPath);
        List<String> uniqueFileName = returnUniqueFileNames(customerList);
        HashMap<String, List<Customer>> hashMap = groupCustomersByName(customerList);

        getRecommendations(uniqueFileName, hashMap, outputPath);
    }

    public static List<Customer> readFile(String inputPath) throws IOException {
        Path fileName = Path.of(inputPath);
        FileReader fileReader = new FileReader(String.valueOf(fileName));
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> lines = new ArrayList<>();
        // skips first two lines
        bufferedReader.readLine();
        bufferedReader.readLine();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        fileReader.close();

        List<Customer> customers = new ArrayList<>();
        for (String list : lines) {
            String[] splitLine = list.split("\t", 7);
            long soId = Long.parseLong(splitLine[0]);
            long ppnkId = Long.parseLong(splitLine[1]);
            String soCountCode = splitLine[2];
            String name = splitLine[3];
            String mobile = splitLine[4];
            String email = splitLine[5];
            String productList = splitLine[6];
            String[] listOfProducts = productList.split(",");

            for (String a : listOfProducts) {
                Customer customer = new Customer(soId, ppnkId, soCountCode, name, mobile, email, a);
                customers.add(customer);
            }
        }

        return customers;
    }

    public static List<String> returnUniqueFileNames(List<Customer> customerList) throws IOException {
        Set<String> uniqueName = new HashSet<>();
        for (Customer name : customerList) {
            uniqueName.add(name.getName());
        }
        List<String> list = new ArrayList<String>();
        for (String x : uniqueName) {
            list.add(x);
        }
        // Reverse list
        Collections.reverse(list);

        return list;
    }

    public static HashMap<String, List<Customer>> groupCustomersByName(List<Customer> customerList) throws IOException {
        HashMap<String, List<Customer>> hashMap = new HashMap<String, List<Customer>>();
        for (Customer customerName : customerList) {
            if (!hashMap.containsKey(customerName.getName())) {
                List<Customer> groupList = new ArrayList<>();
                groupList.add(customerName);

                hashMap.put(customerName.getName(), groupList);
            } else {
                hashMap.get(customerName.getName()).add(customerName);
            }
        }

        return hashMap;
    }

    public static Set<String> compareList(List<Customer> ogList, List<Customer> list) {
        List<Customer> recommendItem = new ArrayList<>();
        for (int i=0; i<ogList.size(); i++) {
            for (int j=0; j<list.size(); j++) {
                if (ogList.get(i).getProductList().equals(list.get(j).getProductList())) {
                    List<Customer> item = new ArrayList<>(list);
                    if (recommendItem.isEmpty()) {
                        recommendItem.addAll(item);
                    }
                    recommendItem.remove(list.get(j));
                }
            }
        }

        Set<String> productList = new HashSet<String>();
        for (Customer customer : recommendItem) {
            productList.add(customer.getProductList());
        }

        return productList;
    }

    public static List<String> compareMultipleList(List<Customer> ogList, List<List<Customer>> otherCustomersList) {
        Set<String> recommendations = new HashSet<String>();
        for (List<Customer> customer : otherCustomersList) {
            recommendations.addAll(compareList(ogList, customer));
        }

        List<String> list = new ArrayList<>();
        for (String x : recommendations) {
            list.add(x);
        }

        return list;
    }

    public static void getRecommendations(List<String> uniqueFileName, HashMap<String, List<Customer>> hashMap, String outputPath) throws IOException {
        FileOutputStream fs = new FileOutputStream(outputPath);
        fs.write("Name\tRecommendation".getBytes());

        for (String name : uniqueFileName) {
            List<Customer> ogList = new ArrayList<>(hashMap.get(name));
            List<List<Customer>> otherCustomersList = new ArrayList<>();
            for (String name2 : uniqueFileName) {
                if (name.equals(name2)) {
                    continue;
                }
                List<Customer> list = new ArrayList<>(hashMap.get(name2));
                otherCustomersList.add(list);
            }
            fs.write("\n".getBytes());
            fs.write(name.getBytes());
            List<String> recommendation = compareMultipleList(ogList, otherCustomersList);
            fs.write("\t".getBytes());
            for (String x : recommendation) {
                fs.write(x.getBytes());
                fs.write(",".getBytes());
            }
        }
        fs.close();
    }
}
