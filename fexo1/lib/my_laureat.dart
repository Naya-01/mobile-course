import 'package:flutter/material.dart';

class MyLaureat extends StatelessWidget {
  final String firstname;
  final String surname;
  final String motivation;

  const MyLaureat(
      {Key? key,
      required this.firstname,
      required this.surname,
      required this.motivation})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
        child: SizedBox(
      width: 300,
      child: Container(
        decoration: BoxDecoration(
            border: Border.all(color: Colors.blue, width: 2)),
        margin: const EdgeInsets.all(15),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Container(
              height: 50,
              color: Colors.blue,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(motivation, style: const TextStyle(color: Colors.white)),
                ],
              ),
              // child: Text(motivation, style: const TextStyle(color: Colors.white), textAlign: TextAlign.center),
            ),
            SizedBox(
              height: 50,
              child: Row(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  Text(firstname + " " + surname),
                ],
              ),
            )
          ],
        ),
      ),
    ));
  }
}

class Nobel extends StatelessWidget {
  final Map<String, dynamic> prizeData;

  const Nobel({Key? key, required this.prizeData}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final laureates = prizeData["laureates"]; // get the list of laureates
    final year = prizeData['year'];
    final category = prizeData['category'];
    final laureateWidgetList = [];
    for (var i = 0; i < laureates.length; ++i) {
      final laureate = laureates[i]; // get a map of a laureate
      laureateWidgetList.add(MyLaureat(
          firstname: laureate['firstname'],
          surname: laureate['surname'],
          motivation: laureate['motivation']));
    }

    return Center(
        child: SizedBox(
      width: 500,
      child: Card(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Container(
              color: Colors.deepPurpleAccent,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Text(year + " : " + category,
                      style: const TextStyle(color: Colors.white)),
                ],
              ),
            ),
            Container(
              decoration: BoxDecoration(
                  border: Border.all(color: Colors.deepPurpleAccent, width: 2)),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[...laureateWidgetList],
              ),
            ),
          ],
        ),
      ),
    ));
    ;
  }
}
