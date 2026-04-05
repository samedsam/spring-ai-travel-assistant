# Travel Assistant — Spring AI Demo

A Spring Boot REST API demonstrating Spring AI ChatClient capabilities.
Built as a companion project for the article on [smartgnt.com](https://smartgnt.com).

## Features
- `/recommend` — AI travel recommendations
- `/itinerary` — Structured day-by-day itinerary
- `/stream` — Streaming narrative travel story
- `/chat` — Conversational assistant with memory
- `/compare` — Side-by-side model comparison (Groq vs OpenAI)

## Stack
- Java 21 / Spring Boot 4.0.5 / Spring AI 2.0.0-M4
- Groq (llama-3.3-70b-versatile) + OpenAI (gpt-4o)
- Maven

## Setup
```bash
export GROQ_API_KEY=your_key
export OPENAI_API_KEY=your_key
mvn spring-boot:run
```

## Author
[smartgnt.com](https://smartgnt.com)
