<?php

    function createTable(&$header, $res){
        $rows = explode("\n", $res);
        $ncol = count($header);
        // Controlla se sono presenti righe non vuote
        if(count($rows) > 0 && !empty(trim($rows[0]))) {
            echo '<table>';
            echo '<thead>';
            echo '<tr>';
            foreach($header as $head){
                echo '<th>' .$head .'</th>';
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
                    foreach($columns as $col){
                      echo '<td>' . htmlspecialchars($col) . '</td>';
                    }
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