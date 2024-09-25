Overview
The MedicalChatbot is a simple rule-based medical advice system built in Java. It helps users get basic medical advice by selecting from a predefined list of diseases and specifying symptoms and severity. The chatbot can provide home remedies or professional medical advice based on the user's selection.

The chatbot features a GUI built using Swing and allows users to:

Select from over 20 diseases.
Choose between home remedies or professional medical advice.
Input additional symptoms and severity level (1-5).
View past interactions and recommendations.
Features
Disease List: The chatbot includes 20+ diseases ranging from common conditions like fever and cold to specific conditions like diabetes and migraines.
Advice Types: The user can select whether they want home remedies or professional medical advice.
Symptom Input: Users can provide additional symptoms which will be considered when generating advice.
Severity Scale: Users can rate the severity of their condition from 1 (mild) to 5 (severe), and the chatbot will adjust its advice accordingly.
History Log: The chatbot saves interactions in a text file so users can review past recommendations.
GUI Interface: A simple and intuitive interface using Java Swing, ensuring a good user experience.
Key Files:
EnhancedMedicalChatbotGUI.java: This is the main program that creates the GUI, handles user input, and provides medical advice based on a simple rule-based system.
user_interactions.txt: This file logs the past interactions users have had with the chatbot, including the selected disease, symptoms, and the advice provided.
How It Works
The user launches the application and is greeted with a simple interface.
The user selects a disease from the drop-down menu.
The user can choose whether they want Home Remedies or Professional Advice.
Optionally, the user can enter additional symptoms and rate the severity of their condition.
The chatbot provides the relevant advice based on the selection, symptoms, and severity.
The chatbot saves the interaction to a file for later review.
Extending the Project
Add More Diseases: You can easily add more diseases, home remedies, and professional advice by updating the homeRemedies and professionalAdvice HashMaps in the code.

Add More Inputs: Add more user inputs like age, gender, or pre-existing conditions to make the chatbot more versatile and specific in its advice.

Enhance the GUI: Further improvements could include enhancing the look and feel of the GUI, adding icons, or even implementing voice interaction.

Integrate with APIs: Consider integrating real-time health data from public health APIs or databases to provide more up-to-date advice.

Potential Future Features
Symptom Checker: Automatically suggest possible diseases based on user-inputted symptoms.
Mobile Version: Port the Java Swing application to a mobile version using JavaFX or a similar framework.
Known Issues
The chatbot uses a simple rule-based system and does not offer real-time diagnostics or medical advice from professionals.
It is a basic educational tool and should not be used for real medical emergencies.
