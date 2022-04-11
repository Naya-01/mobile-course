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
    // var  fist = laureate1["firstname"];
    // var second = laureate1["surname"];
    // var moti = laureate1["motivation"];
    final card = laureate1.map((e) =>
        MyLaureat(firstname: e["firstname"] ?? "rien",
            surname: e["surname"] ?? "rien",
            motivation: e["motivation"] ?? "rien")
    );
    return MaterialApp(
      title: "je sais pas",
      home: Scaffold(
        appBar: AppBar(
          title: const Text("View all Nobel prizes"),
        ),
        body: Center(
          child: SizedBox(
            child: ListView(
              children:  [
                ...card,
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
}];
