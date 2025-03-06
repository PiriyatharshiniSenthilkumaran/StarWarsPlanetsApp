# Star Wars Planets Explorer

The app is a **Star Wars Planets Explorer** that fetches and displays a list of planets from the SWAPI (Star Wars API).

## Features

- **Splash Screen**: A simple splash screen with a loading indicator.
- **Planet List Screen**: Displays a scrollable list of planets in cards with pagination (infinite scrolling).
- **Planet Details Screen**: Shows detailed information about a selected planet.
- **Offline Support**: Caches planet data locally using Room for offline access.
- **Dynamic Images**: Uses Picsum Photos to generate placeholder images for planets.
- **Error Handling**: Gracefully handles loading, error, and empty states.
- **Clean Architecture**: Follows a modular structure with separation of concerns.

---

## Tech Stack

- **Kotlin**: Primary programming language.
- **Jetpack Compose**: Modern UI toolkit for building the app's UI.
- **Retrofit**: For making API calls to SWAPI.
- **Room**: For local caching of planet data.
- **Hilt**: For dependency injection.
- **Coil**: For image loading.
- **Paging 3**: For pagination and infinite scrolling.
- **Material Design**: For a visually appealing and intuitive UI.

---

## Approach

### **Splash Screen**
- A simple splash screen is displayed for 2 seconds before navigating to the main screen.
- Implemented using `LaunchedEffect` and a delay.

### **Planet List Screen**
- The list of planets is fetched from SWAPI using Retrofit.
- Pagination is implemented using Paging 3 to load data in chunks (10 planets per page).
- Each planet is displayed in a card with its name, climate, and a dynamically generated image.
- Infinite scrolling is enabled to load more planets as the user scrolls.

### **Planet Details Screen**
- When a planet card is clicked, the app navigates to the details screen.
- Displays **name, orbital period, gravity, and a larger image**.
- A back button is provided to navigate back to the list screen.

### **Offline Support**
- **Room** is used for local caching.
- The app first tries to fetch data from the API. If the API call fails, it falls back to cached data.
- The cached data is updated whenever new data is fetched.

### **Dynamic Images**
- **Picsum Photos** is used to generate placeholder images for planets.
- The image URL is dynamically generated based on the planet’s position in the list.

### **Error Handling**
- The app handles **loading, error, and empty states gracefully**.
- Error messages are displayed in a **user-friendly** manner.

---

## Trade-offs

### **Offline Support**
✅ Room provides robust offline support.  
❌ A simpler approach (e.g., SharedPreferences) could have been used but wouldn't scale well.

### **Dynamic Images**
✅ Simple implementation using Picsum Photos.  
❌ The images are not related to actual planets.

### **Pagination**
✅ Infinite scrolling provides a smoother user experience.  
❌ Traditional pagination (Next/Previous buttons) would be simpler but less modern.

### **Error Handling**
✅ Generic error messages are displayed.  
❌ More detailed error messages (e.g., API failure vs. no internet) could be added.

---

## 📂 Project Structure
```yaml
app/
├── data/  
│   ├── local/          # Room database and DAOs  
│   ├── remote/         # Retrofit API service and network models  
│   ├── repository/     # Repository for data operations  
│   └── di/             # Hilt dependency injection modules  
├── domain/             # Use cases and business logic  
├── navigation/         # Navigation component  
├── ui/                 # Jetpack Compose screens and components  
│   ├── components/     # Reusable UI components  
│   ├── screens/        # Screens for different app features  
│   ├── theme/          # Theming-related files  
│   └── viewmodel/      # ViewModels for UI state management  




## Installation

### Clone the repository:
```sh
git clone https://github.com/yourusername/star-wars-planets-explorer.git


