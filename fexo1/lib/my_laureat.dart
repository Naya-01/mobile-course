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
      width: 400,
      child: Card(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Container(

              color: Colors.blue,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,

                children:  [
                  Text(motivation),
                ],
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(firstname+" "+surname),
              ],
            ),
          ],
        ),
      ),
    )
  );
}
}
