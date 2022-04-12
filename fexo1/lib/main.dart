import 'package:flutter/material.dart';
import 'package:fexo1/my_laureat.dart';

void main() {
  runApp(const MyApp());
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

  @override
  Widget build(BuildContext context) {
    // final card = laureate1.map((e) =>
    //     MyLaureat(firstname: e["firstname"] ?? "rien",
    //         surname: e["surname"] ?? "rien",
    //         motivation: e["motivation"] ?? "rien")
    // );

    return MaterialApp(
      title: "je sais pas",
      home: Scaffold(
          appBar: AppBar(
            title: const Text("View all Nobel prizes"),
          ),
          body: Center(
            child: SizedBox(
              child: ListView(
                children: const [
                  Nobel(prizeData: prize1)
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
      "motivation": "\"for his empirical contributions to labour \n economics\"",
      "share": "2"
    },
    {
      "id": "1008",
      "firstname": "Joshua",
      "surname": "Angrist",
      "motivation":
      "\"for their methodological contributions to \n the analysis of causal relationships\"",
      "share": "4"
    },
    {
      "id": "1009",
      "firstname": "Guido",
      "surname": "Imbens",
      "motivation":
      "\"for their methodological contributions to \n the analysis of causal relationships\"",
      "share": "4"
    }
  ]
};

