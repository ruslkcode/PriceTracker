# Price Tracker Service

A professional backend monitoring solution designed to track retail product prices in real-time and provide asynchronous notifications via Telegram.

---

## Features

* **Automated Web Scraping**: Utilizes **Jsoup** to extract real-time price data from retail platforms.
* **Real-time Alerts**: Integrated with **Telegram Bot API** to notify users instantly when a price drop occurs.
* **Data Persistence**: Uses **Spring Data JPA** and **PostgreSQL** to store price history and user metadata.
* **Containerization**: Fully packaged with **Docker** for consistent deployment across different environments.
* **Asynchronous Processing**: Non-blocking notification system to ensure high performance.

---

## Tech Stack

* **Language**: Java 17+
* **Framework**: Spring Boot 3
* **Database**: PostgreSQL
* **Scraping**: Jsoup
* **DevOps**: Docker
* **Build Tool**: Maven

---

## Configuration & Security

**Important:** To ensure security, this project uses Environment Variables for sensitive credentials. **Do not hardcode your API tokens.**

### Required Environment Variables:
* `TELEGRAM_BOT_TOKEN`: Your official bot token from @BotFather.
* `DB_USERNAME`: Your PostgreSQL username.
* `DB_PASSWORD`: Your PostgreSQL password.

---

## Installation & Run

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/ruslkcode/PriceTracker.git](https://github.com/ruslkcode/PriceTracker.git)
   cd PriceTracker
