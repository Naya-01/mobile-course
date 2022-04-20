import 'package:flutter/material.dart';

class SendMessage extends StatelessWidget {
  final int click;

  const SendMessage({
    Key? key,
    required this.click,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final _formKey = GlobalKey<FormState>();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Next page '),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              'Hi, you have clicked $click times sinces you started the app!',
              style: const TextStyle(fontSize: 24),
            ),
            const Padding(
              padding: EdgeInsets.symmetric(vertical: 16.0),
            ),
            const MyCustomForm(),
          ],
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
        child: const Icon(Icons.send),
      ),
    );
  }
}

class MyCustomForm extends StatefulWidget {
  const MyCustomForm({Key? key}) : super(key: key);

  @override
  MyCustomFormState createState() {
    return MyCustomFormState();
  }
}

class MyCustomFormState extends State<MyCustomForm> {
  final _formKey = GlobalKey<FormState>();


  @override
  Widget build(BuildContext context) {
    String name="";
    String phone="";
    return Form(
      key: _formKey,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          TextFormField(
            onChanged: (text){
              name=text;
            },
            decoration: const InputDecoration(
              border: OutlineInputBorder(),
              hintText: 'Enter a name',
            ),
            validator: (value) {
              if (value == null || value.isEmpty) {
                return 'Please enter a name';
              }
              return null;
            },
          ),
          const Padding(
            padding: EdgeInsets.symmetric(vertical: 16.0),
          ),
          TextFormField(
            onChanged: (text){
              phone=text;
            },
            decoration: const InputDecoration(
              border: OutlineInputBorder(),
              hintText: 'Enter a phone number',
            ),
            validator: (value) {
              if (value == null || value.isEmpty) {
                return 'Please a phone number';
              }
              return null;
            },
          ),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 16.0),
            child: ElevatedButton(
              onPressed: () {
                if (_formKey.currentState!.validate()) {
                  ScaffoldMessenger.of(context).showSnackBar(
                     SnackBar(
                       content:
                       Text('Hello dear $name, you would like to send a message to this phone number : $phone !'),
                     ),
                  );
                }
              },
              child: const Text('Submit'),
            ),
          ),
        ],
      ),
    );
  }
}

