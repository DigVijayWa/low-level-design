package com.app.services;

import java.util.List;
import java.util.Random;

public class NameService {
    private List<String> names = List.of("Madhava Achaval", "Roodra Lata", "Jayadeva Bakshi", "Srijan Tavade",
            "Raghu Jagatap", "Akshey Ashtikar", "Krishan Mukhtar", "Purshottama Navathe", "Ragunath Bhaumik",
            "Yogarasa Mahanta", "Shudraka Deshmukh", "Amitabh Panda", "Sahadeva Viswan", "Bhima Sarkar", "Vishnu Kamal",
            "Hira Bhattacharya", "Viswamitra Holkar", "Hira Ayyangar", "Vishnu Munshi", "Vakpati Heravdakar");

    public String randomName() {
        int index = new Random().nextInt(names.size());
        String name = names.get(index);
        names.remove(index);
        return name;
    }
}
