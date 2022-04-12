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
      width: 600,
      child: Card(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Container(
              color: Colors.blue,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(motivation),
                ],
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(firstname + " " + surname),
              ],
            ),
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
      width: 1000,
      child: Card(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Container(
              color: Colors.blue,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(year + " : " + category),
                ],
              ),
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[...laureateWidgetList],
            ),
          ],
        ),
      ),
    ));
    ;
  }
}
