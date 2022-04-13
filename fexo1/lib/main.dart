import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:fexo1/my_laureat.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}


Future<List<dynamic>> fetchData() async {
  var url = Uri.parse('https://api.nobelprize.org/v1/prize.json');
  final response = await http.get(url);
  if (response.statusCode == 200) {
    Map<String, dynamic> jsonResponse = json.decode(response.body);
    return jsonResponse["prizes"];
  } else {
    throw Exception('Unexpected error occured!');
  }
}


class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const HomePage();
  }
}

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  Future<List> getData() async {
    final listWidget = [];
    final data = await fetchData();
    for (var element in data) {
      // print("element");
      print(element);
      listWidget.add(
          Nobel(prizeData: element)
      );
    }
    return listWidget;
  }

  @override
  Widget build(BuildContext context) {
    // final test =  fetchData();
    // final NobelCard = [];
    // print(test);
    List<dynamic> test = getData() as List<dynamic>;
    return MaterialApp(
      title: "je sais pas",
      home: Scaffold(
          appBar: AppBar(
            title: const Text("View all Nobel prizes"),
          ),
          body: Center(
            child: SizedBox(
              child: ListView(
                children:   [
                  // Nobel(prizeData: prize1)
                  ...test
                ],
              ),
            ),
          )
      ),
    );
  }
}

const laureate1 = [{
  "id": "1002",
  "firstname": "Benjamin",
  "surname": "List",
  "motivation": "\"for the development of asymmetric organocatalysis\"",
  "share": "2"
}
];

const prize1 = {
  "year": "2021",
  "category": "economics",
  "laureates": [
    {
      "id": "1007",
      "firstname": "David",
      "surname": "Card",
      "motivation": "\"for his empirical contributions to labour  economics\"",
      "share": "2"
    },
    {
      "id": "1008",
      "firstname": "Joshua",
      "surname": "Angrist",
      "motivation":
      "\"for their methodological contributions to  the analysis of causal relationships\"",
      "share": "4"
    },
    {
      "id": "1009",
      "firstname": "Guido",
      "surname": "Imbens",
      "motivation":
      "\"for their methodological contributions to  the analysis of causal relationships\"",
      "share": "4"
    }
  ]
};


