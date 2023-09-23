import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ControleDeEstoque extends JFrame {
    private Map<String, Integer> estoqueConsolesXbox;
    private Map<String, Integer> estoqueConsolesPlayStation;
    private Map<String, Integer> estoqueJogosXbox;
    private Map<String, Integer> estoqueJogosPlayStation;
    private DefaultTableModel modeloTabelaConsolesXbox;
    private DefaultTableModel modeloTabelaConsolesPlayStation;
    private DefaultTableModel modeloTabelaJogosXbox;
    private DefaultTableModel modeloTabelaJogosPlayStation;
    private JTable tabelaEstoqueConsolesXbox;
    private JTable tabelaEstoqueConsolesPlayStation;
    private JTable tabelaEstoqueJogosXbox;
    private JTable tabelaEstoqueJogosPlayStation;

    public ControleDeEstoque() {
        setTitle("Controle de Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        estoqueConsolesXbox = new TreeMap<>();
        estoqueConsolesXbox.put("Xbox Series X", 8);

        estoqueConsolesPlayStation = new TreeMap<>();
        estoqueConsolesPlayStation.put("PlayStation 5", 6);

        estoqueJogosXbox = new TreeMap<>();
        estoqueJogosXbox.put("Halo Infinite", 10);
        estoqueJogosXbox.put("Forza Horizon 5", 8);

        estoqueJogosPlayStation = new TreeMap<>();
        estoqueJogosPlayStation.put("Horizon Forbidden West", 12);
        estoqueJogosPlayStation.put("Ratchet & Clank: Rift Apart", 15);

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BorderLayout());

        modeloTabelaConsolesXbox = new DefaultTableModel();
        modeloTabelaConsolesXbox.addColumn("Console Xbox");
        modeloTabelaConsolesXbox.addColumn("Quantidade");

        for (Map.Entry<String, Integer> entry : estoqueConsolesXbox.entrySet()) {
            modeloTabelaConsolesXbox.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        modeloTabelaConsolesPlayStation = new DefaultTableModel();
        modeloTabelaConsolesPlayStation.addColumn("Console PlayStation");
        modeloTabelaConsolesPlayStation.addColumn("Quantidade");

        for (Map.Entry<String, Integer> entry : estoqueConsolesPlayStation.entrySet()) {
            modeloTabelaConsolesPlayStation.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        modeloTabelaJogosXbox = new DefaultTableModel();
        modeloTabelaJogosXbox.addColumn("Jogo Xbox");
        modeloTabelaJogosXbox.addColumn("Quantidade");

        for (Map.Entry<String, Integer> entry : estoqueJogosXbox.entrySet()) {
            modeloTabelaJogosXbox.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        modeloTabelaJogosPlayStation = new DefaultTableModel();
        modeloTabelaJogosPlayStation.addColumn("Jogo PlayStation");
        modeloTabelaJogosPlayStation.addColumn("Quantidade");

        for (Map.Entry<String, Integer> entry : estoqueJogosPlayStation.entrySet()) {
            modeloTabelaJogosPlayStation.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        JTabbedPane abas = new JTabbedPane();
        tabelaEstoqueConsolesXbox = new JTable(modeloTabelaConsolesXbox);
        tabelaEstoqueConsolesPlayStation = new JTable(modeloTabelaConsolesPlayStation);
        tabelaEstoqueJogosXbox = new JTable(modeloTabelaJogosXbox);
        tabelaEstoqueJogosPlayStation = new JTable(modeloTabelaJogosPlayStation);

        abas.addTab("Consoles Xbox", new JScrollPane(tabelaEstoqueConsolesXbox));
        abas.addTab("Consoles PlayStation", new JScrollPane(tabelaEstoqueConsolesPlayStation));
        abas.addTab("Jogos Xbox", new JScrollPane(tabelaEstoqueJogosXbox));
        abas.addTab("Jogos PlayStation", new JScrollPane(tabelaEstoqueJogosPlayStation));

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto(abas.getSelectedIndex());
            }
        });

        JButton botaoRetirar = new JButton("Retirar");
        botaoRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirarProduto(abas.getSelectedIndex());
            }
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRetirar);

        painelConteudo.add(abas, BorderLayout.CENTER);
        painelConteudo.add(painelBotoes, BorderLayout.SOUTH);

        setContentPane(painelConteudo);
        setVisible(true);
    }

    private void adicionarProduto(int abaSelecionada) {
        DefaultTableModel modeloTabela = null;
        Map<String, Integer> estoque = null;
        JTable tabela = null;

        if (abaSelecionada == 0) {
            modeloTabela = modeloTabelaConsolesXbox;
            estoque = estoqueConsolesXbox;
            tabela = tabelaEstoqueConsolesXbox;
        } else if (abaSelecionada == 1) {
            modeloTabela = modeloTabelaConsolesPlayStation;
            estoque = estoqueConsolesPlayStation;
            tabela = tabelaEstoqueConsolesPlayStation;
        } else if (abaSelecionada == 2) {
            modeloTabela = modeloTabelaJogosXbox;
            estoque = estoqueJogosXbox
            ;
            tabela = tabelaEstoqueJogosXbox;
        } else if (abaSelecionada == 3) {
            modeloTabela = modeloTabelaJogosPlayStation;
            estoque = estoqueJogosPlayStation;
            tabela = tabelaEstoqueJogosPlayStation;
        }

        if (modeloTabela != null && estoque != null && tabela != null) {
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada != -1) {
                String produtoSelecionado = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
                int quantidadeAtual = estoque.get(produtoSelecionado);

                try {
                    String entrada = JOptionPane.showInputDialog("Quantidade a ser adicionada para " + produtoSelecionado + ":");
                    int quantidadeEntrada = Integer.parseInt(entrada);

                    if (quantidadeEntrada > 0) {
                        estoque.put(produtoSelecionado, quantidadeAtual + quantidadeEntrada);
                        modeloTabela.setValueAt(quantidadeAtual + quantidadeEntrada, linhaSelecionada, 1);
                    } else {
                        JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Digite um valor válido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
            }
        }
    }

    private void retirarProduto(int abaSelecionada) {
        DefaultTableModel modeloTabela = null;
        Map<String, Integer> estoque = null;
        JTable tabela = null;

        if (abaSelecionada == 0) {
            modeloTabela = modeloTabelaConsolesXbox;
            estoque = estoqueConsolesXbox;
            tabela = tabelaEstoqueConsolesXbox;
        } else if (abaSelecionada == 1) {
            modeloTabela = modeloTabelaConsolesPlayStation;
            estoque = estoqueConsolesPlayStation;
            tabela = tabelaEstoqueConsolesPlayStation;
        } else if (abaSelecionada == 2) {
            modeloTabela = modeloTabelaJogosXbox;
            estoque = estoqueJogosXbox;
            tabela = tabelaEstoqueJogosXbox;
        } else if (abaSelecionada == 3) {
            modeloTabela = modeloTabelaJogosPlayStation;
            estoque = estoqueJogosPlayStation;
            tabela = tabelaEstoqueJogosPlayStation;
        }

        if (modeloTabela != null && estoque != null && tabela != null) {
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada != -1) {
                String produtoSelecionado = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
                int quantidadeAtual = estoque.get(produtoSelecionado);

                try {
                    String entrada = JOptionPane.showInputDialog("Quantidade a ser retirada para " + produtoSelecionado + ":");
                    int quantidadeSaida = Integer.parseInt(entrada);

                    if (quantidadeSaida > 0 && quantidadeSaida <= quantidadeAtual) {
                        estoque.put(produtoSelecionado, quantidadeAtual - quantidadeSaida);
                        modeloTabela.setValueAt(quantidadeAtual - quantidadeSaida, linhaSelecionada, 1);
                    } else if (quantidadeSaida <= 0) {
                        JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Quantidade insuficiente em estoque.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Digite um valor válido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControleDeEstoque();
            }
        });
    }
}
