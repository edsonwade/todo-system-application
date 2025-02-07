### Here are four more unique records for the `Project` entity:

```json
{
  "name": "backend",
  "description": "Backend development project",
  "tasks": []
}
```

```json
{
  "name": "mobile",
  "description": "Mobile app development project",
  "tasks": []
}
```

```json
{
  "name": "devops",
  "description": "DevOps and infrastructure project",
  "tasks": []
}
```

```json
{
  "name": "qa",
  "description": "Quality assurance and testing project",
  "tasks": []
}
```

### Here are five unique records for the Task entity, each with a unique Project entity:

{
"name": "Design Wireframe",
"description": "Design the wireframe for the new project",
"completed": false,
"dueDate": "2025-09-03",
"project": {
"name": "UI/UX Design",
"description": "User interface and user experience design project"
}
}

{
"name": "Develop Backend",
"description": "Develop the backend services for the application",
"completed": false,
"dueDate": "2025-10-15",
"project": {
"name": "Backend Development",
"description": "Backend services and API development project"
}
}

{
"name": "Create Database Schema",
"description": "Design and create the database schema",
"completed": false,
"dueDate": "2025-11-01",
"project": {
"name": "Database Design",
"description": "Database schema design and implementation project"
}
}

{
"name": "Setup CI/CD Pipeline",
"description": "Setup continuous integration and continuous deployment pipeline",
"completed": false,
"dueDate": "2025-12-20",
"project": {
"name": "DevOps",
"description": "Continuous integration and deployment project"
}
}

POST http://localhost:8081/api/tasks/create-task
Content-Type: application/json

{
"name": "Conduct User Testing",
"description": "Conduct user testing and gather feedback",
"completed": true,
"dueDate": "2026-01-10",
"project": {
"name": "Quality Assurance",
"description": "User testing and quality assurance project"
}
}

### Here are five unique records for the `Project` entity with non-empty `tasks`:

```json
{
  "name": "devops",
  "description": "DevOps and infrastructure project",
  "tasks": [
    {
      "name": "Setup CI/CD Pipeline",
      "description": "Setup continuous integration and continuous deployment pipeline",
      "completed": false,
      "dueDate": "2025-12-20"
    }
  ]
}
```

```json
{
  "name": "frontend",
  "description": "Frontend development project",
  "tasks": [
    {
      "name": "Design Wireframe",
      "description": "Design the wireframe for the new project",
      "completed": false,
      "dueDate": "2025-09-03"
    }
  ]
}
```

```json
{
  "name": "backend",
  "description": "Backend development project",
  "tasks": [
    {
      "name": "Develop Backend",
      "description": "Develop the backend services for the application",
      "completed": false,
      "dueDate": "2025-10-15"
    }
  ]
}
```

```json
{
  "name": "mobile",
  "description": "Mobile app development project",
  "tasks": [
    {
      "name": "Create Mobile App",
      "description": "Develop the mobile application for the project",
      "completed": false,
      "dueDate": "2025-11-30"
    }
  ]
}
```

```json
{
  "name": "qa",
  "description": "Quality assurance and testing project",
  "tasks": [
    {
      "name": "Conduct User Testing",
      "description": "Conduct user testing and gather feedback",
      "completed": false,
      "dueDate": "2026-01-10"
    }
  ]
}
```