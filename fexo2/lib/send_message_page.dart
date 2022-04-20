import 'package:flutter/material.dart';


class SendMessage extends StatelessWidget {
  final int click;
  const SendMessage({
    Key? key,
    required this.click,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Next page '),
      ),
      body:  Center(
        child: Text(
          'il y a $click clic(s)',
          style: const TextStyle(fontSize: 24),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        // When the user presses the button, show an alert dialog containing
        // the text that the user has entered into the text field.
        onPressed: () {
          showDialog(
            context: context,
            builder: (context) {
              return const AlertDialog(
                // Retrieve the text the that user has entered by using the
                // TextEditingController.
                content: Text("envoyer message"),
              );
            },
          );
        },
        tooltip: 'Send message!',
        child: const Icon(Icons.sms),
      ),
    );

  }
}
