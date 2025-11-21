package eu.foxbms.installationchecklist.ui.screens

@Composable
fun ProjectItem(project: ProjectEntity, onClick: () -> Unit, navController: NavController) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(project.name, style = MaterialTheme.typography.bodyLarge)
            Text(project.description, style = MaterialTheme.typography.bodyMedium)
            Button(
                onClick = { navController.navigate("cabinet_specs/${project.id}") },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add Cabinet")
            }
        }
    }
}
