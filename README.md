
# DPOO- ESPOTYFAI Project

## Overview
The **DPOO-ESPOTYFAI** project was developed by **Martí Rebollo, Alex Ferré, Mohammed-Sami Amin, Sandra Corral,** and **Aroa García** as part of the second semester of the Object-Oriented Programming (DPOO) course. The project consists of designing and developing a music streaming application similar to Spotify, with features such as user authentication, music management, and social playlist sharing.

## Project Structure
The project is divided into multiple parts:

1. **User Authentication and Registration**
2. **Music Management System**
3. **Music Player and Statistics**
4. **Playlist Management**
5. **Graphical User Interface (GUI) using Swing**

## Technologies Used

- **Java Swing**: For building the graphical user interface.
- **MySQL**: Database to store information about users, songs, and playlists.
- **Jira**: Task management tool for team collaboration.

## Features

### 1. **User Authentication and Registration**
   - **Login**: Users can log in using their credentials.
   - **Registration**: New users can sign up by entering their email and password, along with other personal details.
   - **Account Management**: Users can delete their accounts and log out at any time.

### 2. **Music Management System**
   - **Song Database**: Users can add new songs to the system and see an instant update.
   - **Song Information**: Clicking on a song shows its details and lyrics.
   - **Error Handling**: Clear notifications for invalid data entry.

### 3. **Music Player and Statistics**
   - **Music Player**: Functionalities like Play, Pause, Stop, Next, Previous, and Repeat.
   - **Song Statistics**: Displaying a breakdown of music genres in graphical form.

### 4. **Playlist Management**
   - **Create Playlists**: Users can create and manage their playlists.
   - **Public Playlists**: Users can make their playlists public, allowing other users to listen to them.
   - **Add Songs to Playlists**: Users can add songs to existing playlists.
   - **Search and Filter**: Songs can be searched by title or other criteria.

### 5. **Graphical User Interface (GUI)**
   - **Custom Components**: Buttons, labels, text fields, and sliders are customized for a better user experience.
   - **Swing Layouts**: Use of GridLayout, FlowLayout, BoxLayout, and BorderLayout to organize components.
   - **Error Dialogs**: JOptionPane used to display error or confirmation messages.

### 6. **Class Diagram and Design**
   - **Model-View-Controller (MVC)**: The project uses the MVC design pattern, separating logic, views, and data management.
   - **UML Class Diagram**: Includes classes like User, Playlist, and Song, with a clear separation of concerns for data and logic handling.

## Development Methodology
The team employed an Agile approach, dividing the project into manageable tasks and using Jira for initial planning. As the project progressed, team communication moved to Discord for smoother collaboration. The task distribution was as follows:

- **Graphics and UI**: One team member focused on the user interface.
- **Controllers and Listeners**: One team member handled the event-driven functionality.
- **Backend Logic**: Two team members worked on implementing the core logic.
- **Database**: One member worked specifically on database interaction and SQL queries.

## Screenshots

### 1. **Login Screen**
   - The login page has custom buttons and text fields designed to offer a sleek, user-friendly interface.
   ![Login Screen](https://github.com/user-attachments/assets/919b02f3-893e-4464-abc0-5570a8423756)


### 2. **Registration Screen**
   - The registration screen includes text fields for the user’s details and buttons for submitting data.
   ![Registration Screen](https://github.com/user-attachments/assets/ecd1188f-2c6a-4b51-8c2d-168b2792b8be)


### 3. **Main Screen**
   - Upon login, the main screen displays all songs in the database, allowing users to manage and play their music.
   ![Main Screen](https://github.com/user-attachments/assets/1e8dc4d3-2ed7-4ddd-88ba-3f03fdfba036)


### 4. **Song Information**
   - Clicking on a song shows detailed information, including lyrics and the ability to add it to a playlist.
   ![Song Information](https://github.com/user-attachments/assets/113a7d2d-00df-4429-a1b2-fafab60fae79)


### 5. **Statistics View**
   - The statistics page displays a histogram of the most listened genres.
   ![Statistics View](https://github.com/user-attachments/assets/1410aab8-0204-415a-8007-3791fd388c51)


## Database Schema
The database is structured with the following tables:

- **Users**: Contains user information, including email, password, and registration date.
- **Songs**: Stores song details like title, artist, genre, and file path.
- **Playlists**: Stores user-created playlists and their associated songs.
- **Song_Stats**: Tracks user interactions with songs for statistical analysis.

## Conclusion
This project has been an excellent opportunity to apply object-oriented design principles in a real-world scenario. We have learned the importance of clear communication, task delegation, and adhering to design patterns like MVC. Additionally, we have gained valuable experience in database management, GUI design, and backend development.

## Future Improvements
- **Cloud Integration**: For cross-device synchronization.
- **Social Features**: Implementing user interactions like following and messaging.
- **Enhanced Music Player**: Adding features like shuffle and play history.

