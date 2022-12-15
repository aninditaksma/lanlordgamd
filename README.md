# Fight The Landlord

Fight the landlord is a popular card game also known as Dou Dizhu, it involves 3 players, one of which is the "Landlord" and the other two are the "Farmers". Landlord and Farmers race against each other to get rid of their cards, while attempting to win the rounds by playing card combinations that out ranks the opponents.

## Prerequisites

* JavaFX
* JDK 15, or at least JDK 11

## Installation
1. Clone the repo
   ```sh
   git clone https://git-teaching.cs.bham.ac.uk/mod-team-project-2020/malmo.git
   ```
2. Import LandlordGame as Java Project to your desire IDE or Editor
3. [Download](https://gluonhq.com/products/javafx/) and add JavaFX library by follow this guide [https://openjfx.io/openjfx-docs/]
4. Edit Run Configurations by adding VM options with your own path to the JavaFX **/lib** directory
   ```sh
   --module-path insert_path_here/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml
   ```
5. Run `Main.java`