<?php    
    $rows = explode("\n", $res);

    function createTable($header){
        $ncol = count($header);      
        // Controlla se sono presenti righe non vuote
        if(count($rows) > 0 && !empty(trim($rows[0]))) {
            echo '<table>';
            echo '<thead>';
            echo '<tr>';
            for($h = 0; $h < $rows; $h++){
                echo '<th>' .$header[$h] .'</th>';
            }
            echo '</tr>';
            echo '</thead>';
            echo '<tbody>';
            
            // Per ogni riga, suddivide i campi utilizzando preg_split per gestire eventuali spazi multipli
            foreach($rows as $row) {
                $row = trim($row);
                if(empty($row)) continue;
                $columns = preg_split('/\s+/', $row);
                // Controlla che ci sia il numero di colonne necessario
                if(count($columns) >= $ncol) {
                    echo '<tr>';
                    echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[1]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[2]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[3]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[4]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[5]) . '</td>';
                    echo '</tr>';
                }
            }
            
            echo '</tbody>';
            echo '</table>';
        } else {
            echo '<p>Nessun dato ricevuto.</p>';
        }
    }
?>