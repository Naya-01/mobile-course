import 'package:flutter/material.dart';
import 'dart:io' show Platform;
import 'package:flutter/foundation.dart' show kIsWeb;
import 'package:flutter_sms/flutter_sms.dart';

class SendMessage extends StatefulWidget {
  const SendMessage({Key? key}) : super(key: key);

  @override
  State<SendMessage> createState() => _SendMessageState();
}

class _SendMessageState extends State<SendMessage> {
  @override
  Widget build(BuildContext context) {
    final count = ModalRoute.of(context)!.settings.arguments;
    final _formKey = GlobalKey<FormState>();
    final nameController = TextEditingController();
    final phoneController = TextEditingController();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Next page '),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              'Hi, you have clicked $count times sinces you started the app!',
              style: const TextStyle(fontSize: 16),
            ),
            const Padding(
              padding: EdgeInsets.symmetric(vertical: 16.0),
            ),
            MyCustomForm(
                name: nameController, phone: phoneController, formu: _formKey),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        // When the user presses the button, show an alert dialog containing
        // the text that the user has entered into the text field.
        onPressed: () {
          if (_formKey.currentState!.validate()) {
            if(kIsWeb){
              showDialog(
                context: context,
                builder: (context) {
                    return const AlertDialog(
                      // Retrieve the text the that user has entered by using the
                      // TextEditingController.
                      content:
                      Text(
                          "Sorry, you cannot send SMS on this platform !"
                      ),
                    );
                  
                },
              ); 
            }else{
              List<String> recipents = [phoneController.text];
              _sendSMS("Hello dear ${nameController.text}, you have clicked $count times", recipents);
            }
          }
        },
        tooltip: 'Send message!',
        child: const Icon(Icons.send),
      ),
    );
  }
}

class MyCustomForm extends StatefulWidget {
  final TextEditingController name;
  final TextEditingController phone;
  final formu;

  const MyCustomForm(
      {Key? key, required this.name, required this.phone, required this.formu})
      : super(key: key);

  @override
  MyCustomFormState createState() {
    return MyCustomFormState();
  }
}

class MyCustomFormState extends State<MyCustomForm> {
  @override
  Widget build(BuildContext context) {
    final name = widget.name.text;
    final phone = widget.phone.text;
    return Form(
      key: widget.formu,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          TextFormField(
            controller: widget.name,
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
          if(!kIsWeb)
            TextFormField(
              controller: widget.phone,
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
        ],
      ),
    );
  }
}

void _sendSMS(String message, List<String> recipents) async {
  String _result = await sendSMS(message: message, recipients: recipents)
      .catchError((onError) {
    print(onError);
  });
  print(_result);
}
